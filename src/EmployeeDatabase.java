import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.io.*;

public class EmployeeDatabase {
    public static List<Employee> readDataset(String filePath) {
        List<Employee> employees = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = new Employee(data[0], Integer.parseInt(data[1].trim()), data[2], Double.parseDouble(data[3].trim()));
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
