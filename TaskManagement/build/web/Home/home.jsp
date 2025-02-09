<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/Home.css">
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <ul>
            <li>Dashboard</li>
            <li>Components</li>
            <li>Tables</li>
            <li>Forms</li>
            <li>Widgets</li>
            <li>Charts</li>
            <li>Maps</li>
        </ul>
    </div>
    
    <div class="main-content">
        <div class="navbar">
            <img src="Image/profile.png" class="icon" onclick="showModal('accountModal')" alt="Profile">
        </div>
        
        <h1>Dashboard</h1>
        <div class="dashboard-boxes">
            <div class="box">Facebook Stats</div>
            <div class="box">Twitter Stats</div>
            <div class="box">LinkedIn Stats</div>
            <div class="box">Google+ Stats</div>
        </div>
    </div>
    
    <div id="accountModal" class="modal" style="display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5);">
        <div class="modal-content" style="background-color: white; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 300px; text-align: center;">
            <span class="close" onclick="closeModal('accountModal')" style="color: red; float: right; font-size: 28px; font-weight: bold; cursor: pointer;">&times;</span>
            <h2>Chỉnh sửa Profile & Account</h2>
            <p>Thông tin chỉnh sửa Profile và Account ở đây...</p>
        </div>
    </div>
    
    <script>
        function showModal(modalId) {
            document.getElementById(modalId).style.display = 'block';
        }
        
        function closeModal(modalId) {
            document.getElementById(modalId).style.display = 'none';
        }
    </script>
</body>
</html>
