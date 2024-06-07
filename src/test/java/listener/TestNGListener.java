package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestNGListener implements ITestListener {
	
	private static final Logger logger = LogManager.getLogger(ITestListener.class);

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/reports";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    public void onTestStart(ITestResult result) {
        // Capture test start information (optional)
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        updateTestResult(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        updateTestResult(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        updateTestResult(result, "SKIP");
    }

    private void updateTestResult(ITestResult result, String status) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO test_results (test_name, test_class, status) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, result.getMethod().getMethodName());
            statement.setString(2, result.getTestClass().getName());
            statement.setString(3, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}