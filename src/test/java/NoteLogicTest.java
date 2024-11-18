import example.note.NoteLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Класс тестирования NoteLogic
 */
public class NoteLogicTest {

    private NoteLogic noteLogic;

    /**
     * Очистка noteLogic перед каждым запуском тестов
     */
    @BeforeEach
    public void setUp() {
        noteLogic = new NoteLogic();
    }

    /**
     * Проверка добавления заметки
     */
    @Test
    public void addAndNotesTest(){
        String addResult = noteLogic.handleMessage("/add myFirstNote");
        String notesResult = noteLogic.handleMessage("/notes");
        Assertions.assertEquals("Note added!: myFirstNote", addResult);
        Assertions.assertEquals("Your notes:\n myFirstNote", notesResult);
    }


    /**
     * Проверка редактирования заметки
     */
    @Test
    public void editTest(){
        noteLogic.handleMessage("/add myFirstNote");
        String editResult = noteLogic.handleMessage("/edit myFirstNote mySecondNote");
        String notesResult = noteLogic.handleMessage("/notes");
        Assertions.assertEquals("Note edited!: myFirstNote -> mySecondNote", editResult);
        Assertions.assertEquals("Your notes: \n mySecondNote", notesResult);
    }

    /**
     * Проверка удаления заметки
     */
    @Test
    public void deleteTest(){
        noteLogic.handleMessage("/add myFirstNote");
        noteLogic.handleMessage("/add mySecondNote");
        String delResult = noteLogic.handleMessage("/del myFirstNote");
        String notes = noteLogic.handleMessage("/notes");
        Assertions.assertEquals("Note deleted!: myFirstNote", delResult);
        Assertions.assertEquals("Your notes: \n mySecondNote", notes);

    }

}
