<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty subHeadline}"><c:set var="subHeadline" value="Main page" scope="request"/></c:if>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/head.jsp"/>
<body>
<div class="container">
    <c:import url="fragments/navbar.jsp"/>


    <div class="row">
        <div class="col-sm-6">
            <div class="form-group">
                <div class="input-group date" id="datetimepicker" data-target-input="nearest">
                    <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker"/>
                    <div class="input-group-append" data-target="#datetimepicker" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="border border-danger rounded-lg  text-center m-2 p-2">
        <h3>Kilocalories</h3>
        <div class="progress">
            <div class="progress-bar bg-danger" role="progressbar" style="width: 15%" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <hr>
        <div class="d-flex">
            <div class="m-2 p-2 flex-fill border"><h5>Goal</h5>            <h2>2000</h2></div>
            <div class="m-2 p-2 flex-fill border"><h5>Consumed</h5>        <h2>300</h2></div>
            <div class="m-2 p-2 flex-fill border"><h5>Left</h5>            <h2>1700</h2></div>
        </div>
    </div>
    <div class="d-flex text-center">
        <div class="m-2 p-2 flex-fill border border-success rounded-lg">
            <div class="progress">
                <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25"
                     aria-valuemin="0" aria-valuemax="100">25/100
                </div>
            </div>
            <h5 class="mt-2 pt-2">Fats</h5>
        </div>
        <div class="m-2 p-2 flex-fill border border-info rounded-lg">
            <div class="progress">
                <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50"
                     aria-valuemin="0" aria-valuemax="100">75/150
                </div>
            </div>
            <h5 class="mt-2 pt-2">Proteins</h5>
        </div>
        <div class="m-2 p-2 flex-fill border border-warning rounded-lg">
            <div class="progress">
                <div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75"
                     aria-valuemin="0" aria-valuemax="100">100/150
                </div>
            </div>
            <h5 class="mt-2 pt-2">Carbohydrates</h5>
        </div>
    </div>
    <div class="card m-3">
        <div class="card-header">
            <div class="d-flex  eaten-control" data-repast="1">
                <div class="p-2 flex-grow-1">Breakfast</div>
                <div class="m-0 p-0"><input type="text" class="form-control food-search" id="breakfastFoodSearch" placeholder="Search.."></div>
                <div class="m-0 p-0"><input type="text" class="form-control food-amount" id="breakfastFoodAmount" placeholder="g."></div>
                <div class="m-0 p-0"><button type="button" class="btn btn-primary food-add" id="breakfastFoodAdd">Add</button></div>
            </div>
        </div>
        <div class="card-body">
            <div id="breakfast-table"></div>
        </div>
        <div class="card-footer text-muted">
            <div class="progress">
                <div class="progress-bar bg-danger" role="progressbar" style="width: 30%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100">Kilocalories: 49.0/300</div>
            </div>
        </div>
    </div>
    <div class="card m-3">
        <div class="card-header">
            <div class="d-flex eaten-control" data-repast="2">
                <div class="p-2 flex-grow-1">Lunch</div>
                <div class="m-0 p-0"><input type="text" class="form-control food-search" id="lunchFoodSearch" placeholder="Search.."></div>
                <div class="m-0 p-0"><input type="text" class="form-control food-amount" id="lunchFoodAmount" placeholder="g."></div>
                <div class="m-0 p-0"><button type="button" class="btn btn-primary food-add" id="lunchFoodAdd">Add</button></div>
            </div>
        </div>
        <div class="card-body">
            <div id="lunch-table"></div>
        </div>
        <div class="card-footer text-muted">
            <div class="progress">
                <div class="progress-bar bg-danger" role="progressbar" style="width: 30%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100">Kilocalories: 49.0/300</div>
            </div>
        </div>
    </div>
    <div class="card m-3">
        <div class="card-header">
            <div class="d-flex  eaten-control" data-repast="3">
                <div class="p-2 flex-grow-1">Dinner</div>
                <div class="m-0 p-0"><input type="text" class="form-control food-search" id="dinnerFoodSearch" placeholder="Search.."></div>
                <div class="m-0 p-0"><input type="text" class="form-control food-amount" id="dinnerFoodAmount" placeholder="g."></div>
                <div class="m-0 p-0"><button type="button" class="btn btn-primary food-add" id="dinnerFoodAdd">Add</button></div>
            </div>
        </div>
        <div class="card-body">
            <div id="dinner-table"></div>
        </div>
        <div class="card-footer text-muted">
            <div class="progress">
                <div class="progress-bar bg-danger" role="progressbar" style="width: 30%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100">Kilocalories: 49.0/300</div>
            </div>
        </div>
    </div>
    <div class="card m-3">
        <div class="card-header">
            <div class="d-flex  eaten-control" data-repast="4">
                <div class="p-2 flex-grow-1">Snack</div>
                <div class="m-0 p-0"><input type="text" class="form-control food-search" id="snackFoodSearch" placeholder="Search.."></div>
                <div class="m-0 p-0"><input type="text" class="form-control food-amount" id="snackFoodAmount" placeholder="g."></div>
                <div class="m-0 p-0"><button type="button" class="btn btn-primary food-add" id="snackFoodAdd">Add</button></div>
            </div>
        </div>
        <div class="card-body">
            <div id="snack-table"></div>
        </div>
        <div class="card-footer text-muted">
            <div class="progress">
                <div class="progress-bar bg-danger" role="progressbar" style="width: 30%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100">Kilocalories: 49.0/300</div>
            </div>
        </div>
    </div>

    <c:import url="fragments/footer.jsp"/>
</div><!-- /.container -->
</body>
</html>