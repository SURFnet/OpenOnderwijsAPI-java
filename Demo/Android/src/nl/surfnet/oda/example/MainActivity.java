package nl.surfnet.oda.example;

import java.util.List;

import nl.surfnet.oda.EntityHandler;
import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.NetworkError;
import nl.surfnet.oda.OnderwijsDataAPI;
import nl.surfnet.oda.persons.Person;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * An example of how to use the Surfnet APIWrapper and the APIClient together.
 *
 * @author Daniel Zolnai
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView numberOfPersons = (TextView)findViewById(R.id.numberOfPersons);
        final TextView firstPerson = (TextView)findViewById(R.id.firstPerson);
        // Create a new client for our API
        OnderwijsDataAPI apiClient = new OnderwijsDataAPI("http://surfnetapi.pagekite.me/");
        // Retrieve the persons info using the PersonsClient

        apiClient.getPersonsClient().get("1", new EntityHandler<Person>() {

            @Override
            public void success(Person person) {
                // display the name of the first person
                firstPerson.setText("The first person is called: " + person.getDisplayName());
            }

            @Override
            public void failure(NetworkError e) {
                // if an error happened, inform the user
                e.printStackTrace();
                firstPerson.setText("Error in getting first person :-(");
            }
        });

        apiClient.getPersonsClient().getList(null, new ListHandler<Person>() {

            @Override
            public void success(List<Person> list) {
                // display the number of persons in the UI
                numberOfPersons.setText("There are " + list.size() + " persons.");
            }

            @Override
            public void failure(NetworkError e) {
                // if an error happened, inform the user
                e.printStackTrace();
                numberOfPersons.setText("Error in listing persons :-(");
            }
        });

    }
}
