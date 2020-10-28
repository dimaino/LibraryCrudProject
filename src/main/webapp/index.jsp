<%@ include file= "header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Theme Library</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
  body {
    font: 20px Montserrat, sans-serif;
    line-height: 1.8;
    color: #f5f6f7;
  }
  p {font-size: 16px;}
  .margin {margin-bottom: 45px;}
  .bg-1 { 
    background-color: #E6E6FA; /* Green */
    color: #000000;
  }
  .bg-2 { 
    background-color: #474e5d; /* Dark Blue */
    color: #ffffff;
  }
  .bg-3 { 
    background-color: #FFFFF0; /* White */
    color: #555555;
  }
  .bg-4 { 
    background-color: #2f2f2f; /* Black Gray */
    color: #fff;
  }
  .container-fluid {
    padding-top: 70px;
    padding-bottom: 70px;
  }
  </style>
</head>
<body>
<!-- First Container -->
<div class="container-fluid bg-1 text-center">
  <h3 class="margin">Welcome to the Library Management System</h3>
  <img src="https://upload.wikimedia.org/wikipedia/commons/2/21/Biblioth%C3%A8que_de_l%27Assembl%C3%A9e_Nationale_%28Lunon%29.jpg" class="img-responsive img-circle margin" style="display:inline" alt="Bird" width="350" height="350">
</div>

<!-- Second Container -->
<div class="container-fluid bg-2 text-center">

</div>

<!-- Third Container (Grid) -->
<div class="container-fluid bg-3 text-center">    
  <h3 class="margin">Knowledge is Power!</h3><br>
  <div class="row">
    <div class="col-sm-4">
    
      <img src="https://upload.wikimedia.org/wikipedia/commons/e/e1/Duke_Humfrey%27s_Library_Interior_6%2C_Bodleian_Library%2C_Oxford%2C_UK_-_Diliff.jpg" class="img-responsive margin" style="width:100%; length:100%" alt="Image">
    </div>
    <div class="col-sm-4"> 
    
      <img src="https://upload.wikimedia.org/wikipedia/commons/3/34/Bookshelf_at_Yale.jpg" class="img-circle" style="width:100%; length:100%" alt="Image">
    </div>
    <div class="col-sm-4"> 

      <img src="https://upload.wikimedia.org/wikipedia/commons/4/4b/Long_Room_Interior%2C_Trinity_College_Dublin%2C_Ireland_-_Diliff.jpg"
      class="img-responsive margin" style="width:100%; length:100%" alt="Image">
    </div>
  </div>
</div>


<%@ include file= "footer.jsp" %>