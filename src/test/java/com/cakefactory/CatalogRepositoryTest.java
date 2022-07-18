package com.cakefactory;

import com.cakefactory.model.ItemEntity;
import com.cakefactory.repository.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CatalogRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CatalogRepository catalogRepository;

    @Test
    void canSaveCorrectEntityTest() {
        this.testEntityManager.persist(new ItemEntity("01", "Spring", BigDecimal.ONE));
        var user = catalogRepository.findById("01").orElseThrow();
        assertThat(user.getTitle()).isEqualTo("Spring");
        assertThat(user.getPrice()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void canFetchEntitiesFromDatabaseTest() {
        var items = catalogRepository.findAll();
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getTitle()).isEqualTo("Red Velvet");
    }
}
