<%-- 
    Document   : ballot_box
    Created on : 16/06/2019, 23:50:57
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html-->


<!DOCTYPE html>
<html>

<head>
    <title>Vota Web</title>
    <link rel="icon" href="img/vw-logo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- CSS LINK -->
    <link rel="stylesheet" type="text/css" href="static/css/ballot_box.css">
    <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
    <script type="text/javascript">
        var currentStep = 1;
        var candidateNumber = "";
            
        function nextStep() {
            $.ajax({
                type: 'POST',
                url: '/Vota/display',
                data: {step: currentStep},
                contentType: "application/json; charset=utf-8",
                dataType: 'html'
            }).done(function(response) {
                $('#display').html(response);
            }).fail(function() {
                alert("fail");
            });
        }
        
        function corrige() {
            candidateNumber = "";
            nextStep();
        }

        function confirma() {
            nums = $(".empty");
            
            if(nums.length === 0) {
                $.ajax({
                    type: 'POST',
                    url: '/Vota/confirm_vote',
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json'
                }).done(function(response) {
                    console.log(response);
                    if (response.confirmed === true) {
                        currentStep += 1;
                        nextStep();
                    }
                }).fail(function() {
                    alert("fail");
                });
            }
        }
        
        function loadCandidate() {
            $.ajax({
                type: 'POST',
                url: '/Vota/load_candidate',
                data: {number: candidateNumber},
                contentType: "application/json; charset=utf-8",
                dataType: 'html'
            }).done(function(response) {
                $('#display').html(response);
                candidateNumber = "";
            }).fail(function() {
                alert("fail");
            });
        }

        function numberPress(button) {
            number = button.textContent;
            nums = $(".empty");
            if (nums.length > 0) {
                num = nums[0];
                $(num).removeClass("empty");
                num.textContent = number;
                candidateNumber += number;
                if(nums.length === 1) {
                    loadCandidate();
                }
            }
        }
        
        $(document).ready(function() {
           var auto_ref = setInterval(() => {
                $.get('/Vota/request_response', (result) => {
                    //$('#voting_requests').html(result);
                    if (result.enabled === true) {
                        clearInterval(auto_ref);
                        nextStep();
                    }
                });
            }, 1000);
        });
    </script>
</head>

<body>
    <!-- NAVBAR -->
    <div class="topnav" id="myTopnav">
        <a href="#home.html" style="letter-spacing: 2px; font-size: 1.3rem;">Vota Web</a>
    </div>
    <!-- /NAVBAR -->

    <section class="grid-container">
        <div class="cx cx-1">

            <div id="display" class="cx cx-2">
                <div class="loading">
                    <img src="static/img/loading.gif" alt="aguarde">
                    <p>Aguarde...</p>
                </div>

                <!-- VISUALIZAÇÃO DOS NÚMEROS -->
                <!--div class="cx simple">
                    <div class="dep-fed">
                        <div class="dep-fed-title"><b>Deputado Federal:</b></div>
                        <br>
                        <div class="dep-fed-nums">
                            <div class="num">{{1}}</div>
                            <div class="num">{{2}}</div>
                            <div class="num">{{3}}</div>
                            <div class="num">{{4}}</div>
                        </div>
                    </div>
                </div-->


                <!-- <div class="cx simple"> -->
                <!-- <div class="dep-est"> -->
                <!-- <div class="dep-est-title"><b>Deputado Estadual:</b></div> -->
                <!-- <br> -->
                <!-- <div class="dep-est-nums"> -->
                <!-- <div class="num">{{1}}</div> -->
                <!-- <div class="num">{{2}}</div> -->
                <!-- <div class="num">{{3}}</div> -->
                <!-- <div class="num">{{4}}</div> -->
                <!-- <div class="num">{{5}}</div> -->
                <!-- </div> -->
                <!-- </div> -->
                <!-- </div> -->


                <!-- <div class="cx simple"> -->
                <!-- <div class="sen-1"> -->
                <!-- <div class="sen-1-title"><b>Senador 1:</b></div> -->
                <!-- <br> -->
                <!-- <div class="sen-1-nums"> -->
                <!-- <div class="num">{{1}}</div> -->
                <!-- <div class="num">{{2}}</div> -->
                <!-- <div class="num">{{3}}</div> -->
                <!-- </div> -->
                <!-- </div> -->
                <!-- </div> -->


                <!-- <div class="cx simple"> -->
                <!-- <div class="sen-2"> -->
                <!-- <div class="sen-2-title"><b>Senador 2:</b></div> -->
                <!-- <br> -->
                <!-- <div class="sen-2-nums"> -->
                <!-- <div class="num">{{1}}</div> -->
                <!-- <div class="num">{{2}}</div> -->
                <!-- <div class="num">{{3}}</div> -->
                <!-- </div> -->
                <!-- </div> -->
                <!-- </div> -->


                <!-- <div class="cx simple"> -->
                <!-- <div class="gov"> -->
                <!-- <div class="gov-title"><b>Governador:</b></div> -->
                <!-- <br> -->
                <!-- <div class="gov-nums"> -->
                <!-- <div class="num">{{1}}</div> -->
                <!-- <div class="num">{{2}}</div> -->
                <!-- </div> -->
                <!-- </div> -->
                <!-- </div> -->


                <!-- <div class="cx simple"> -->
                <!-- <div class="pres"> -->
                <!-- <div class="pres-title"><b>Presidente:</b></div> -->
                <!-- <br> -->
                <!-- <div class="pres-nums"> -->
                <!-- <div class="num">{{1}}</div> -->
                <!-- <div class="num">{{2}}</div> -->
                <!-- </div> -->
                <!-- </div> -->
                <!-- </div> -->


                <!-- INFORMAÇÕES POLÍTICOS -->
                <!-- <div class="info"> -->
                <!-- <div class="photo"><img src="img/perfil.jpg" alt="perfil"></div> -->
                <!-- <div class="info-text"> -->
                <!-- <div class="name-poli"><b>{{NOME POLÍTICO}}</b></div> -->
                <!-- <div class="num-poli"><b>{{NÚMERO}}</b></div> -->
                <!-- <div class="part-poli">{{PARTÍDO POLÍTICO}}</div> -->
                <!-- </div> -->
                <!-- </div> -->


                <!-- CONFIRMAÇÃO -->
                <!-- <div style="text-align: center"> -->
                    <!-- <img class="check" src="img/check.png" alt="check"> -->
                <!-- </div> -->
                    

                <!-- FIM -->
                <!-- <div class="fim"><b>Fim</b></div> -->


            </div>

            <div class="cx cx-3">
                <div class="cx cx-4"><b>Vota Web</b></div>
                <bu class="cx cx-5">
                    <div class="cx cx-6">
                        <button class="cx nums 1" onclick="numberPress(this)">1</button>
                        <button class="cx nums 2" onclick="numberPress(this)">2</button>
                        <button class="cx nums 3" onclick="numberPress(this)">3</button>
                        <button class="cx nums 4" onclick="numberPress(this)">4</button>
                        <button class="cx nums 5" onclick="numberPress(this)">5</button>
                        <button class="cx nums 6" onclick="numberPress(this)">6</button>
                        <button class="cx nums 7" onclick="numberPress(this)">7</button>
                        <button class="cx nums 8" onclick="numberPress(this)">8</button>
                        <button class="cx nums 9" onclick="numberPress(this)">9</button>
                        <div class="espaço"></div>
                        <button class="cx nums 0">0</button>
                    </div>
                    <div class="cx cx-7">
                        <button class="cx final-btn branco"><b>Branco</b></button>
                        <button class="cx final-btn corrige" onclick="corrige()"><b>Corrige</b></button>
                        <button class="cx final-btn confirma" onclick="confirma()"><b>Confirma</b></button>
                    </div>
            </div>
        </div>
        </div>
    </section>
    
    <% if (false) { %>
    <!--script>
        function myFunction() {
            var x = document.getElementById("myTopnav");
            if (x.className === "topnav") {
                x.className += " responsive";
            } else {
                x.className = "topnav";
            }
        }
    </script-->
    <% } %>

</body>

</html>
