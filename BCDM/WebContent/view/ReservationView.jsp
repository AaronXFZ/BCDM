<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
  <html>
    <head>
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <link rel="stylesheet" href="style.css">

      <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
            

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body>

        <nav>
            <div class="nav-wrapper main-navbar">
              <a href="#" class="brand-logo">Order ID: 3 </a>
              <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="sass.html">Previous Selection</a></li>
                <li><a href="badges.html">Lookup</a></li>
                <li><a href="collapsible.html">Account</a></li>
              </ul>
            </div>
          </nav>
          
          <div class="row main-box">

            <div class="col s5 newname">
                <div class="left-box">
                    <nav>
                        <div class="nav-wrapper mini-navbar ">
                          <a href="#" class="right">Ericsson Marin</a>
                          <!-- <a href="#">Table 5</a> -->
                          <ul id="nav-mobile" class="left hide-on-med-and-down">
                            <li><a href="#">Order ID: 1</a></li>
                            <li><a href="#">Table: 3</a></li>
                          </ul>
                        </div>
                      </nav>
    
    
                    <table class="items-table">
                        <thead>
                          <tr>
                              <th>Name</th>
                              <th>Quantity</th>
                              <th>Cash</th>
                              <th>Total</th>
                          </tr>
                        </thead>
                
                        <tbody>
                          <tr class="custom-color">
                            <td>Chicken Tacos</td>
                            <td>1</td>
                            <td>$2.00</td>
                            <td>$2.00</td>
                          </tr>
                        </tbody>
                      </table> 
    
                </div>
                

                  <div class="col s12 bottom-column">
                      <div class="row">
                          <div class="col  actions"><button class="action-button">Tabs</button></div>
                          <div class="col  actions"><button class="action-button">+New</button></div>
                          <div class="col  actions"><button class="action-button">Split</button></div>
                          <div class="col actions"><button class="action-button">Cancel</button></div>
                          <div class="col actions"><button class="action-button">Hold</button></div>
                          <div class="col  actions"><button class="action-button">Held</button></div>
                          <div class="col  actions"><button class="action-button custom-color">Send</button></div>
                      </div>


                      <div class="row">
                        <div class="col s6">
                            <div class="row">
                                <div class="col s8">
                                    <ul class="left-pay">
                                        <li>Credits</li>
                                        <li>Discounts</li>
                                        <li>Tax(2%)</li>
                                    </ul>
                                </div>
                                <div class="col s4">
                                    <ul class="left-pay">
                                        <li>$0.00</li>
                                        <li>$0.00</li>
                                        <li>$0.00</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col s6">
                            <div class="row">
                                <div class="col s8">
                                    <ul class="right-pay">
                                        <li>Subtotal</li>
                                        <li>Reservation Fee</li>
                                        <li>Balance Due</li>
                                        <br>
                                        <li><b><h5>Total</h5></b></li>
                                    </ul>
                                </div>
                                <div class="col s4">
                                    <ul class="right-pay">
                                        <li>$14.00</li>
                                        <li>$3.00</li>
                                        <li><span style="color: red;">$14.99</span></li>
                                        <li><h4>$17.00</h4></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col s3 "><button class="bottom-buttons" >Orders</button></div>
                            <div class="col s3 "><button class="bottom-buttons">Dining</button></div>
                            <div class="col s3 "><button class="bottom-buttons">No sale</button></div>
                            <div class="col s3 "><button class="bottom-buttons custom-color">Pay</button></div>
                        </div>


                      </div>


                  </div>
              <!-- Grey navigation panel -->
            </div>
      
            <div class="col s6 right-box ">

                <div class="row">
                    <div class="col s3 food-items"><button class="food-item cc2" > Entire Menu </button> </div>
                    <div class="col s3 food-items "><button class="food-item cc2"> Dishes </button></div>
                    <div class="col s3 food-items "> <button class="food-item cc2"> Beverage </button> </div>

                </div>


                <div class="row">
                    <div class="col s3 food-items"><button class="food-item"> Cheeseburger </button> </div>
                    <div class="col s3 food-items "><button class="food-item check"> Chicken Taco </button></div>
                    <div class="col s3 food-items "> <button class="food-item"> Beef Taco </button> </div>
                    <div class="col s3 food-items "> <button class="food-item"> Slice of Cake </button></div>
                </div>

                <div class="row">
                    <div class="col s3 food-items"><button class="food-item"> Seafood Gumbo </button> </div>
                    <div class="col s3 food-items "><button class="food-item"> Snickers </button></div>
                    <div class="col s3 food-items "> <button class="food-item"> Cotton Candy </button> </div>
                    <div class="col s3 food-items "> <button class="food-item"> Bottled Water </button></div>
                </div>

                <div class="row">
                    <div class="col s3 food-items"><button class="food-item"> Fountain Drink </button> </div>
                    <div class="col s3 food-items "><button class="food-item"> Coffee </button></div>
                    <div class="col s3 food-items "> <button class="food-item"> Milk </button> </div>
                    <div class="col s3 food-items "> <button class="food-item"> Milkshake </button></div>
                </div>


              <!-- Teal page content  -->
            </div>
      
          </div>


      <!--JavaScript at end of body for optimized loading-->
      <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
  </html>