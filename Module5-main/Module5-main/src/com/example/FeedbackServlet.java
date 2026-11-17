package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FeedbackServlet handles feedback submissions via HTTP POST.
 * 
 * NOTE: If your server uses Tomcat 10 or later (Jakarta EE), 
 * replace "javax.servlet" with "jakarta.servlet" in the import statements.
 */
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Set response content type and character encoding
        response.setContentType("text/html;charset=UTF-8");

        // 2. Read form input parameters using request.getParameter()
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String feedbackType = request.getParameter("feedbackType");
        String comments = request.getParameter("comments");

        // Fallback for null values if accessed directly
        if (name == null || name.trim().isEmpty()) {
            name = "Valued User";
        }
        if (email == null) {
            email = "Not provided";
        }
        if (feedbackType == null) {
            feedbackType = "General";
        }
        if (comments == null) {
            comments = "No comments provided.";
        }

        // 3. Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy 'at' hh:mm:ss a");
        String formattedDateTime = now.format(formatter);

        // 4. Generate HTML response
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("    <title>Feedback Confirmation</title>");
            out.println("    <style>");
            out.println("        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }");
            out.println("        body { background: linear-gradient(135deg, #e0eafc, #cfdef3); min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; }");
            out.println("        .card { background: #ffffff; border-radius: 12px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1); width: 100%; max-width: 550px; padding: 35px 30px; }");
            out.println("        .success-badge { display: inline-block; background-color: #c6f6d5; color: #22543d; font-size: 13px; font-weight: 700; padding: 5px 12px; border-radius: 20px; margin-bottom: 15px; }");
            out.println("        h2 { color: #2d3748; margin-bottom: 20px; font-size: 24px; }");
            out.println("        .detail-group { margin-bottom: 16px; border-bottom: 1px solid #edf2f7; padding-bottom: 12px; }");
            out.println("        .detail-label { font-size: 13px; color: #718096; font-weight: 600; text-transform: uppercase; letter-spacing: 0.5px; }");
            out.println("        .detail-value { font-size: 16px; color: #2d3748; margin-top: 4px; line-height: 1.5; }");
            out.println("        .comment-box { background: #f7fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 12px; margin-top: 6px; white-space: pre-wrap; color: #4a5568; }");
            out.println("        .btn-back { display: inline-block; margin-top: 20px; text-decoration: none; background-color: #3182ce; color: white; padding: 10px 20px; border-radius: 6px; font-weight: 600; font-size: 14px; transition: background-color 0.2s; }");
            out.println("        .btn-back:hover { background-color: #2b6cb0; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class=\"card\">");
            out.println("        <span class=\"success-badge\">&#10003; Submission Received</span>");
            out.println("        <h2>Thank you, " + escapeHtml(name) + " for your feedback!</h2>");
            
            out.println("        <div class=\"detail-group\">");
            out.println("            <div class=\"detail-label\">Email Address</div>");
            out.println("            <div class=\"detail-value\">" + escapeHtml(email) + "</div>");
            out.println("        </div>");

            out.println("        <div class=\"detail-group\">");
            out.println("            <div class=\"detail-label\">Feedback Type</div>");
            out.println("            <div class=\"detail-value\">" + escapeHtml(feedbackType) + "</div>");
            out.println("        </div>");

            out.println("        <div class=\"detail-group\">");
            out.println("            <div class=\"detail-label\">Comments</div>");
            out.println("            <div class=\"comment-box\">" + escapeHtml(comments) + "</div>");
            out.println("        </div>");

            out.println("        <div class=\"detail-group\" style=\"border-bottom: none;\">");
            out.println("            <div class=\"detail-label\">Submission Date & Time</div>");
            out.println("            <div class=\"detail-value\">" + formattedDateTime + "</div>");
            out.println("        </div>");

            out.println("        <a href=\"feedback.html\" class=\"btn-back\">&larr; Submit Another Feedback</a>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to feedback form
        response.sendRedirect("feedback.html");
    }

    /**
     * Helper to prevent HTML injection in user input.
     */
    private String escapeHtml(String str) {
        if (str == null) return "";
        return str.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#x27;");
    }
}
