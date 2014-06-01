<?php

if (empty($_POST['author']) || empty($_POST['msg']) || empty($_POST['timestamp']) || empty($_POST['target']) || empty($_POST['root']))
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
$msg = $_POST['msg'];
$time = $_POST['timestamp'];
$root = $_POST['root'];
mysqli_query($con,"INSERT INTO Comments (author,target,message,timestamp,root) VALUES ('$author', '$target', '$msg', '$time', '$root')");

if (mysqli_error($con))
  { die(mysqli_error($con)); } 

mysqli_close($con);
}
?>						