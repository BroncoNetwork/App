<?php

if (empty($_POST['username']) || empty($_POST['password']) || empty($_POST['email']))
  { echo 'You haven\'t filled out all fields correctly. Please go back and try again.'; } 
else
{
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];
mysqli_query($con,"INSERT INTO User (username, password, Email) VALUES ('$username', '$password', '$email')");

if (mysqli_error($con))
  { die(mysqli_error($con)); } 

mysqli_close($con);
}
?>						