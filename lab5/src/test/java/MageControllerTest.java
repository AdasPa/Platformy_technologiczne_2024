import org.example.Mage;
import org.example.MageController;
import org.example.MageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MageControllerTest {


    private MageRepository repository;

    private MageController controller;

    @BeforeEach
    public void setUp() {
        repository = mock(MageRepository.class);
        controller = new MageController(repository);
    }

    @Test
    public void find() {
        Mage mage = new Mage("Merlin", 10);
        when(repository.find("Merlin")).thenReturn(Optional.of(mage));

        String result = controller.find("Merlin");

        assertEquals("Merlin", result);
    }

    @Test
    public void findNotFound() {
        when(repository.find("NonExisting")).thenReturn(Optional.empty());

        String result = controller.find("NonExisting");

        assertEquals("not found", result);
    }

    @Test
    public void delete() {
        doNothing().when(repository).delete("Merlin");

        String result = controller.delete("Merlin");

        assertEquals("done", result);
    }

    @Test
    public void deleteNotFound() {
        doThrow(new IllegalArgumentException("Mage with name NonExisting does not exist")).when(repository).delete("NonExisting");

        String result = controller.delete("NonExisting");

        assertEquals("not found", result);
    }


    @Test
    public void save() {
        doNothing().when(repository).save(new Mage("Gandalf", 100));

        String result = controller.save("Gandalf", 1);
        assertEquals("done", result);
    }

    @Test
    public void saveBadRequest() {
        doThrow(new IllegalArgumentException()).when(repository).save(any(Mage.class));
        String result = controller.save("Gandalf", 10);
        assertEquals("bad request", result);
    }

}
