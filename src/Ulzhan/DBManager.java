package Ulzhan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private Connection connection;
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ulzhan_bd?useUnicode=true&serverTimezone=UTC","root", ""
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCar(Car car){
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO cars (id, name, price, engine_volume) " +
                    "VALUES (NULL, ?, ?, ?)"
            );
            statement.setString(1, car.getName());
            statement.setInt(2, car.getPrice());
            statement.setDouble(3, car.getEngineVolume());
            int rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Car> getAllCars(){
        ArrayList<Car> carList = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                double engineVolume = resultSet.getDouble("engine_volume");
                carList.add(new Car(id, name, price, engineVolume));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return carList;
    }
    public void deleteCar(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM cars WHERE id = ?"
            );
            statement.setLong(1, id);
            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}