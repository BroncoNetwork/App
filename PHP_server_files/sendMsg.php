<?php

if (empty($_POST['author']) || empty($_POST['target']) || empty($_POST['timestamp']) || empty($_POST['message']))
  { echo 'You haven\'t filled out all fields correctly. Please go back and try again.'; } 
else
{
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$author = $_POST['author'];
$target = $_POST['target'];
$timestamp = $_POST['timestamp'];
$msg = $_POST['message'];
mysqli_query($con,"INSERT INTO User (username, password, Email) VALUES ('$username', '$password', '$email')");

if (mysqli_error($con))
  { die(mysqli_error($con)); } 

mysqli_close($con);
}
?>						