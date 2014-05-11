<?php
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$username = $_GET['username'];
$password = $_GET['password'];
$result = mysqli_query($con,"SELECT * FROM User Where username='$username' and password='$password'");
$row = mysqli_fetch_array($result);
$data = $row[0];

if($row)
{
echo "Connected";
}
mysqli_close($con);
?>						