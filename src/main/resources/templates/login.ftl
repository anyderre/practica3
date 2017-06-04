<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>


        /* Base CSS */
        .alignleft {
            float: left;
            margin-right: 15px;
        }
        .alignright {
            float: right;
            margin-left: 15px;
        }
        .aligncenter {
            display: block;
            margin: 0 auto 15px;
        }
        a:focus { outline: 0 solid }
        img {
            max-width: 100%;
            height: auto;
        }
        .fix { overflow: hidden }
        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            margin: 0 0 15px;
            font-weight: 700;
        }
        html,
        body { height: 100% }

        a {
            -moz-transition: 0.3s;
            -o-transition: 0.3s;
            -webkit-transition: 0.3s;
            transition: 0.3s;
            color: #333;
        }
        a:hover { text-decoration: none }



        .search-box{margin:80px auto;position:absolute;}
        .caption{margin-bottom:50px;}
        .loginForm input[type=text], .loginForm input[type=password]{
            margin-bottom:10px;
        }
        .loginForm input[type=submit]{
            background:#fb044a;
            color:#fff;
            font-weight:700;

        }


        #pswd_info {
            background: #dfdfdf none repeat scroll 0 0;
            color: #fff;
            left: 20px;
            position: absolute;
            top: 115px;
        }
        #pswd_info h4{
            background: black none repeat scroll 0 0;
            display: block;
            font-size: 14px;
            letter-spacing: 0;
            padding: 17px 0;
            text-align: center;
            text-transform: uppercase;
        }
        #pswd_info ul {
            list-style: outside none none;
        }
        #pswd_info ul li {
            padding: 10px 45px;
        }



        .valid {
            background: rgba(0, 0, 0, 0) url("https://s19.postimg.org/vq43s2wib/valid.png") no-repeat scroll 2px 6px;
            color: green;
            line-height: 21px;
            padding-left: 22px;
        }

        .invalid {
            background: rgba(0, 0, 0, 0) url("https://s19.postimg.org/olmaj1p8z/invalid.png") no-repeat scroll 2px 6px;
            color: red;
            line-height: 21px;
            padding-left: 22px;
        }


        #pswd_info::before {
            background: #dfdfdf none repeat scroll 0 0;
            content: "";
            height: 25px;
            left: -13px;
            margin-top: -12.5px;
            position: absolute;
            top: 50%;
            transform: rotate(45deg);
            width: 25px;
        }
        #pswd_info {
            display:none;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 text-center">
            <div class="search-box">
                <div class="caption">
                    <h3>Favor introducir su correo y contrasena</h3>
                </div>
                <form action="" class="loginForm">
                    <div class="input-group">
                        <input type="email" id="email" class="form-control" placeholder="exemple@gmail.com">
                        <input type="password" id="paw" class="form-control" placeholder="Password">
                        <input type="submit" id="submit" class="form-control" value="Submit">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>