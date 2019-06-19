<%-- 
    Document   : bulletin
    Created on : 18/06/2019, 22:33:16
    Author     : Lucy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

<head>
    <title>Boletim</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/table.css">
    
    <script type="text/javascript" src="static/js/jquery-3.4.0.min.js"></script>
    <script type="text/javascript">
        
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
                $.get('/Vota/reporter', (result) => {
                    $('#table').html(result);
                    if (result.enabled === true) {
                        clearInterval(auto_ref);
                        nextStep();
                    }
                });
            }, 3000);
        });
    </script>
</head>

<body>

    <!-- NAVBAR -->
    <div class="topnav" id="myTopnav">
        <a href="#home.html" style="letter-spacing: 2px; font-size: 1.3rem;">Vota Web</a>
    </div>
    <!-- /NAVBAR -->

    <table id="table">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Usuário</th>
            <th>Evento</th>
        </tr>
    </table>

    
</body>

</html>
