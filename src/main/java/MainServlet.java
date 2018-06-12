import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter writer = response.getWriter();
        writer.write(loadPage("pages/fileUpload.html"));
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

        String fileName = null;
        try {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            System.out.println("fileName = " + fileName);

            InputStream fileContent = null;
            try {
                fileContent = filePart.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("output/"+fileName);
                int size = fileContent.available();
                byte[] b = new byte[size];
                fileContent.read(b);
                fileOutputStream.write(b);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (NullPointerException e) {
                // e.printStackTrace();
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public String loadPage(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)), "utf-8");
    }
}