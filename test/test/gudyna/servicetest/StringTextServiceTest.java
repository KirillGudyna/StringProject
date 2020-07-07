package test.gudyna.servicetest;

import com.gudyna.stringtask.exception.ProgramException;
import com.gudyna.stringtask.service.StringTextService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class StringTextServiceTest {
    StringTextService service;
    String string;

    @BeforeTest
    public void setUp() {
        service = new StringTextService();
        string = "Hello, my name is Kirill Gudyna";
    }

    @Test
    public void replaceLetterTest() {
        String expected = "Helho, my namh is Kirhll Gudhna";
        try {
            String actual = service.replaceLetter(4, string, 'h');
            assertEquals(expected, actual);
        } catch (ProgramException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void replaceOneWithAnotherTest() {
        String expected = "Hjllo, my name is Kirill Gudyna";
        try {
            String actual = service.replaceOneWithAnother(string, 'h', 'e', 'j');
            assertEquals(expected, actual);
        } catch (ProgramException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void replaceOneWordsWithAnotherTest(){
        String expected = "HI, my name is Kirill Gudyna";
        try {
            String actual = service.replaceOneWordsWithAnother(string,"Hello","HI");
            assertEquals(expected, actual);
        } catch (ProgramException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void replaceWordByLengthTest(){
        String expected = "Hello, my Kirill is Kirill Gudyna";
        try {
            String actual = service.replaceWordByLength(string,4,"Kirill");
            assertEquals(expected, actual);
        } catch (ProgramException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void deleteAllNonLetterCharactersTest(){
        String expected = "Hello my name is Kirill Gudyna";
        try {
            String actual = service.deleteAllNonLetterCharacters(string);
            assertEquals(expected, actual);
        } catch (ProgramException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void deleteSpecificWordsTest(){
        String expected = "Hello, my  is Kirill Gudyna";
        try {
            String actual = service.deleteSpecificWords(string,4);
            assertEquals(expected, actual);
        } catch (ProgramException e) {
            fail(e.getMessage());
        }
    }
}
