import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Rectangle;

public class CVGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image file");

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
        try {
            // Getting personal information
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            if (name.length() < 3 || name.length() > 32) {
                JOptionPane.showMessageDialog(null, "Error: Name must be between 2 and 32 characters long.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Name is saved: " + name);
            } 

            System.out.println("Enter your address:");
            String address = scanner.nextLine();
            if (address.length() < 3 || address.length() > 32) {
                JOptionPane.showMessageDialog(null, "Error: address must be between 2 and 32 characters long.", "Invalid address", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Address is saved: " + address);
            } 

            System.out.println("Enter your phone number:");
            String phoneNumber = scanner.nextLine();
            if (phoneNumber.length() < 3 || phoneNumber.length() > 16) {
                JOptionPane.showMessageDialog(null, "Error: phoneNumber must be between 3 and 16 characters long.", "Invalid PhoneNumber", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("PhoneNumber is saved: " + phoneNumber);
            } 
            System.out.println("Enter your email address:");
            String email = scanner.nextLine();
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(null, "Error: Email address must contain '@'.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Email is valid: " + email);
            }
            // Getting education details
            System.out.println("Enter your highest degree:");
            String degree = scanner.nextLine();

            System.out.println("Enter your university:");
            String university = scanner.nextLine();

            System.out.println("Enter your graduation year:");
            int graduationYear = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Getting work experience details
            System.out.println("Enter your work experience (job title, company, duration):");
            String workExperience = scanner.nextLine();

            // Getting skills
            System.out.println("Enter your skills (comma-separated list):");
            String skills = scanner.nextLine();

            // Getting references
            System.out.println("Enter your references:");
            String references = scanner.nextLine();

            // Getting cover letter
            System.out.println("Enter your cover letter:");
            String coverLetter = scanner.nextLine();

            // Getting profile
            System.out.println("Enter your profile:");
            String profile = scanner.nextLine();
           
            Font redBoldFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.RED);
            
            // Generating the content that the user has typed 
            String cvContent = "                                                    Curriculum Vitae OF " + name + "\n\n"
            		 + "\n" 
                     + "\n" 
                     + "\n" 
                     + "\n"   
                    + "Name: " + name + "\n"
                    + "Address: " + address + "\n"
                    + "Phone: " + phoneNumber + "\n"
                    + "Email: " + email + "\n\n"
                    + "Education:\n"
                    + "Degree: " + degree + "\n"
                    + "University: " + university + "\n"
                    + "Graduation Year: " + graduationYear + "\n\n"
                    + "Work Experience:\n" + workExperience + "\n\n"
                    + "Skills:\n";

            String[] skillList = skills.split(",");
            for (String skill : skillList) {
                cvContent += "- " + skill.trim() + "\n";
            }

            cvContent += "\nReferences:\n" + references + "\n\n";
            cvContent += "Cover Letter:\n" + coverLetter + "\n\n";
            cvContent += "Profile:\n" + profile + "\n\n";

            // Specify the file path to save the CV
            String pdfFilePath = "C:/Users/ArenahoMuhali-LFI/OneDrive - Linkfields innovations/Pictures/Screenshots/CV.pdf";
            String imagePath = "C:/Users/ArenahoMuhali-LFI/Downloads/ROLLS.jpg/";
            // Write content to the specified PDF file path
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            // Add the background image
            Image backgroundImage = Image.getInstance(imagePath);
            Rectangle pageSize = document.getPageSize();
            backgroundImage.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight());
            backgroundImage.setAbsolutePosition(0, 0);
            
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.addImage(backgroundImage);

            Image image = Image.getInstance(selectedFile.getAbsolutePath());
            
            // Set the image to the top-left corner
            image.setAbsolutePosition(0, PageSize.A4.getHeight() / 4);

            
            // Scale the image to fit if it is larger than the page
            if (image.getScaledWidth() > PageSize.A4.getWidth()) {
                image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                image.setAbsolutePosition(0, PageSize.A4.getHeight() - image.getScaledHeight());
                float x = 0; // X-coordinate
                float y = PageSize.A4.getHeight() / 2; // Y-coordinate (top of rectangle)
                float width = 200; // Width of rectangle
                float height = PageSize.A4.getHeight() / 2; // Height of rectangle

                // Scale the image to fit within the rectangle
                image.scaleToFit(width, height);
                image.setAbsolutePosition(x, 720);

            }
            
            document.add(image);
            
            
            // Add CV content
            document.add(new Paragraph(cvContent));

            document.close();

            System.out.println("CV saved as PDF successfully!");

        } catch (DocumentException | IOException e) {
            System.out.println("An error occurred while writing the CV file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    }}
