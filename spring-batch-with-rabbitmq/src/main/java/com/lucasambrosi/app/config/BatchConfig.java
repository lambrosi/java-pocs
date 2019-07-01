package com.lucasambrosi.app.config;

import com.lucasambrosi.app.model.Contato;
import com.lucasambrosi.app.processor.ContatoProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    protected Job exportContacts(
            @Qualifier("convertFromRabbit") Step convertFromRabbit) {
        return jobBuilderFactory.get("exportContacts")
                .start(convertFromRabbit)
                .build();
    }

    @Qualifier("convertFromRabbit")
    @Bean
    protected Step convertFromRabbit(
            @Qualifier("rabbitReader") ItemReader<Map<Object, Object>> rabbitReader,
            @Qualifier("contatoProcessor") ContatoProcessor contatoProcessor,
            @Qualifier("databaseContatoWriter") JdbcBatchItemWriter<Contato> databaseContatoWriter,
            @Qualifier("transactionManager") ResourcelessTransactionManager transactionManager) {
        return stepBuilderFactory.get("convertMessage")
                .transactionManager(transactionManager)
                .<Map<Object, Object>, Contato> chunk(10)
                .reader(rabbitReader)
                .processor(contatoProcessor)
                .writer(databaseContatoWriter)
                .build();
    }

    @Qualifier("rabbitReader")
    @Bean
    protected ItemReader<Map<Object, Object>> rabbitReader() {
        return new AmqpItemReader<>(rabbitTemplate);
    }

    @Qualifier("databaseContatoWriter")
    @Bean
    public JdbcBatchItemWriter<Contato> databaseContatoWriter(@Qualifier("contatoDataSource") DataSource dataSource) {
        JdbcBatchItemWriter<Contato> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        jdbcBatchItemWriter.setSql("INSERT INTO contato(nome, endereco, telefone) VALUES (:nome, :endereco, :telefone)");

        return jdbcBatchItemWriter;
    }

    @Qualifier("contatoDataSource")
    @Bean
    public DataSource contatoDataSource(
            @Value("${datasource.driver}") String dbDriver,
            @Value("${datasource.url}") String dbUrl,
            @Value("${datasource.user}") String dbUser,
            @Value("${datasource.password}") String dbPassword) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    @Qualifier("transactionManager")
    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        //Não define datasource para que o SpringBatch não crie suas
        //tabelas auxiliares para controle dos jobs
    }

}
