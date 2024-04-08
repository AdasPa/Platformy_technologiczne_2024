import org.example.MageRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


import org.example.Mage;


import static org.junit.jupiter.api.Assertions.assertEquals;

class MageRepositoryTest {
    
    @Test
    void find() {
        MageRepository repository = new MageRepository();
        repository.save(new Mage("Merlin", 100));
        assertEquals("Mage{name='Merlin', level=100}", repository.find("Merlin").get().toString());
    }

    @Test
    void findReturnEmptyOptional() {
        MageRepository repository = new MageRepository();
        assertFalse(repository.find("Merlin").isPresent());
    }

    @Test
    void save() {
        MageRepository repository = new MageRepository();

        repository.save(new Mage("Merlin", 100));

        assertThat(repository.find("Merlin")).hasValueSatisfying(mage -> {
            assertThat(mage.getName()).isEqualTo("Merlin");
            assertThat(mage.getLevel()).isEqualTo(100);
        });
    }

    @Test
    void saveThrowIllegalArgumentException()
    {
        MageRepository repository = new MageRepository();

        repository.save(new Mage("Merlin", 100));
        assertThat(repository.find("Merlin")).hasValueSatisfying(mage -> {
            assertThat(mage.getName()).isEqualTo("Merlin");
            assertThat(mage.getLevel()).isEqualTo(100);
        });

        assertThrows(IllegalArgumentException.class, () -> {repository.save(new Mage("Merlin", 100));});
    }

    @Test
    void delete() {
        MageRepository repository = new MageRepository();

        repository.save(new Mage("Merlin", 100));
        repository.delete("Merlin");
        assertThat(repository.find("Merlin")).isEmpty();
    }

    @Test
    void deleteThrowIllegalArgumentException() {
        MageRepository repository = new MageRepository();
        assertThrows(IllegalArgumentException.class, () -> repository.delete("Merlin"));
    }
}