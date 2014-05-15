<?php

if (empty($_POST['author']) || empty($_POST['msg']) || empty($_POST['timestamp']) || empty($_POST['target']))
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
mysqli_query($con,"INSERT INTO Posts (author,msg,timestamp, target) VALUES ('$author', '$msg', '$timestamp', '$target')");

if (mysqli_error($con))
  { die(mysqli_error($con)); } 

mysqli_close($con);
}
?>						