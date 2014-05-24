<?php
 
if (empty($_POST['username']))
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
  $username = $_POST['username'];
  $result = mysqli_query($con,"SELECT * FROM Messages Where target='$username'");
 
  while($row = mysqli_fetch_array($result))
  {
     echo $row[0]."|".$row[1]."|".$row[2]."|".$row[3]."\n";
  }
 } 
 mysqli_close($con);
}
?>