<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback</title>
    <style>
        /* Add your styles here */
    </style>
</head>
<body>

<!-- Feedback Form -->
<section id="feedback">
    <div class="container">
        <h2>Feedback</h2>
        <p>We'd love to hear your feedback! Please share your thoughts with us:</p>
        <form action="/feedback" method="post">
            <input type="text" name="name" placeholder="Your Name" required><br>
            <input type="email" name="email" placeholder="Your Email" required><br>
            <textarea name="feedback" placeholder="Your Feedback" rows="4" required></textarea><br>
            <button type="submit">Submit Feedback</button>
        </form>
    </div>
</section>

</body>
</html>
