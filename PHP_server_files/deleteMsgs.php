<?php

if (empty($_POST['target']) || empty($_POST['author']) || empty($_POST['timestamp']))
  { echo 'You haven\'t filled out all fields correctly. Please go back and try again.'; } 
else
{
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");

 if (mysqli_connect_errno($con))
 {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {
  $author = $_POST['author'];
  $target = $_POST['from'];
  $timestamp = $_POST['timestamp'];
  $result = mysqli_query($con,"DELETE from Messages WHERE author = '$author' AND target = '$target' AND timestamp = '$timestamp'");
  
  if (mysqli_error($con))
  { echo "Failed to connect to MySQL: " . mysqli_connect_error(); } 
  
 } 
 mysqli_close($con);
}
?>					