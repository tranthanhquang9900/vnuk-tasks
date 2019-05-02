package vn.edu.vnuk.tasks.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.vnuk.task.jdbc.ConnectionFactory;
import vn.edu.vnuk.tasks.model.Task;

public class TaskDao {
	
    private Connection connection;

    public TaskDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public TaskDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Task contact) throws SQLException{

        String sqlQuery = "insert into task (description, is_complete, date_of_completion) "
                        +	"values (?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, contact.getDescription());
                statement.setBoolean(2, contact.getIsComplete());
                statement.setDate(3, new Date(contact.getDateOfcompletion().getTimeInMillis()));

                // 	Executing statement
                statement.execute();

                System.out.println("New record in DB !");

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                System.out.println("Done !");
        }

    }
    
    
    //  READ (List of Contacts)
    @SuppressWarnings("finally")
    public List<Task> read() throws SQLException {

        String sqlQuery = "select * from task";
        PreparedStatement statement;
        List<Task> contacts = new ArrayList<Task>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            while(results.next()){

                Task contact = new Task();
                contact.setId(results.getLong("id"));
                contact.setDescription(results.getString("description"));
                contact.setIsComplete(results.getBoolean("is_complete"));
                Calendar date = Calendar.getInstance();
                date.setTime(results.getDate("date_of_completion"));
                contact.setDateOfcompletion(date);

                contacts.add(contact);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                return contacts;
        }


    }


    //  READ (Single Contact)
    @SuppressWarnings("finally")
    public Task read(Long id) throws SQLException{

        String sqlQuery = "select * from task where id=?";

        PreparedStatement statement;
        Task contact = new Task();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	contact.setId(results.getLong("id"));
                contact.setDescription(results.getString("description"));
                contact.setIsComplete(results.getBoolean("is_complete"));
                Calendar date = Calendar.getInstance();
                date.setTime(results.getDate("date_of_completion"));
                contact.setDateOfcompletion(date);

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                return contact;
        }

    }


    //  UPDATE
    public void update(Task contact) throws SQLException {
        String sqlQuery = "update task set description=?, is_complete=?," 
                            + "date_of_completion=? where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, contact.getDescription());
            statement.setBoolean(2, contact.getIsComplete());
            statement.setDate(3, new Date(contact.getDateOfcompletion().getTimeInMillis()));
            statement.setLong(4, contact.getId());
            statement.execute();
            statement.close();
            
            System.out.println("Task successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Task contact) throws SQLException {
        String sqlQuery = "delete from task where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, contact.getId());
            statement.execute();
            statement.close();
            
            System.out.println("Task successfully deleted.");

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}