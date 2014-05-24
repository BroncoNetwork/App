<?php

if (empty($_POST['to']))
  { echo 'You haven\'t filled out all fields correctly. Please go back and try again.'; } 
else
{
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$to = $_POST['to'];
$result = mysqli_query($con,"select * from User where username = '$to'");

if (empty($result)) {
   echo 'DNE'
} else {
   echo 'Exists'
}

mysqli_close($con);
}
?>						