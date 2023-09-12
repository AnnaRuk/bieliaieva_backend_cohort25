package repository.Impl;

import models.Event;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("EventRepositoryFileImpl is works ...")
class EventRepositoryFileImplTest {

    private static final String TEMP_FILE_NAME = "events_test.txt";

    private EventRepositoryFileImpl eventRepositoryFile;
    @BeforeEach
    public void setUp() throws Exception {
        createNewFileForTest(TEMP_FILE_NAME);
        eventRepositoryFile = new EventRepositoryFileImpl(TEMP_FILE_NAME);
    }

    @AfterEach
    public void tearDown() throws Exception{
    deleteFileAfterTest(TEMP_FILE_NAME);
    }
    @DisplayName("addEvent():")
    @Nested
    class addEventTest {


    @Test
    public void add_event_writes_line_to_file() throws Exception {
        LocalDate beginData = LocalDate.of(2023,12,05);
        LocalDate endData = LocalDate.of(2023,12,06);
        Event event = new Event("TODO1", beginData,endData);
        eventRepositoryFile.addEvent(event);
        String expected = "1|TODO1|2023-12-05|2023-12-06";
        BufferedReader reader = new BufferedReader(new FileReader(TEMP_FILE_NAME));
        String actual = reader.readLine();
        reader.close();
        assertEquals(expected,actual);

    }

}

    @DisplayName("getAllEvents():")
    @Nested
    class getAllEventsTest {

        @Test
        public void get_all_returns_correct_list_of_events() throws Exception {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_NAME));
            writer.write("1|TODO1|2023-12-05|2023-12-06");
            writer.newLine();
            writer.write("2|TODO2|2023-12-06|2023-12-08");
            writer.close();


            List<Event> expected = Arrays.asList(
                    new Event(1L,"TODO1",
                            LocalDate.of(2023,12, 5),
                            LocalDate.of(2023,12,6)),
                    new Event(2L,"TODO2",
                            LocalDate.of(2023,12,6),
                            LocalDate.of(2023,12,8))
            );
        List<Event> actual = eventRepositoryFile.getAllevents();

        assertEquals(expected,actual);



        }
    }




    private static void createNewFileForTest(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                throw new IllegalStateException("File doesn't del");
            }
        }
        boolean result = file.createNewFile();
        if (!result) {
            throw new IllegalStateException("File doesn't create");
        }
    }

    private void deleteFileAfterTest(String fileName){
        File file = new File(fileName);
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                throw new IllegalStateException("File doesn't del");
            }
        }
    }
}
