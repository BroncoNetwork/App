<?php
 
if (empty($_POST['target']))
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
  $course = $_POST['target'];
  $result = mysqli_query($con,"SELECT author,target,message,timeStamp FROM Post Where target='$course' ORDER BY id");
 
  while($row = mysqli_fetch_array($result))
  {
     echo $row[1]."|".$row[2]."|".$row[3]."|".$row[4]."\n";
  }
 } 
 mysqli_close($con);
}
?>