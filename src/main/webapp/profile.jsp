<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/head.jsp"/>
<body>
<div class="container">
    <jsp:include page="fragments/navbar.jsp"/>
    <div class="card">
        <div class="card-header">
            Information about me
        </div>
        <div class="card-body">
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableFirstName" class="col-sm-4 col-form-label">First name</label>
                        <input type="text" disabled class="form-control" id="editableFirstName" value="<c:out value="${profile.firstName}"/>"
                               aria-describedby="editableFirstName-buttons">
                        <div class="input-group-append" id="editableFirstName-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">First Name</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableLastName" class="col-sm-4 col-form-label">LastName</label>
                        <input type="text" disabled class="form-control" id="editableLastName" value="${profile.lastName}"
                               aria-describedby="editableLastName-buttons">
                        <div class="input-group-append" id="editableLastName-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Last name</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableEmail" class="col-sm-4 col-form-label">Email</label>
                        <input type="email" disabled class="form-control" id="editableEmail" value="${profile.email}"
                               aria-describedby="editableEmail-buttons">
                        <div class="input-group-append" id="editableEmail-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">This email will receive letters and interesting news</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableBirthday" class="col-sm-4 col-form-label">Your birthday</label>
                        <input type="text" disabled class="form-control" id="editableBirthday" value="${profile.birthday}"
                               aria-describedby="editableBirthday-buttons">
                        <div class="input-group-append" id="editableBirthday-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your birthday</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableWeight" class="col-sm-4 col-form-label">Weight</label>
                        <input type="text" disabled class="form-control" id="editableWeight" value="${profile.weight}"
                               aria-describedby="editableWeight-buttons">
                        <div class="input-group-append" id="editableWeight-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your physical parameter</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableHeight" class="col-sm-4 col-form-label">Height</label>
                        <input type="text" disabled class="form-control" id="editableHeight" value="${profile.height}"
                               aria-describedby="editableHeight-buttons">
                        <div class="input-group-append" id="editableHeight-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your physical parameter</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editablePhysicalLoadLevel" class="col-sm-4 col-form-label">Physical load level</label>
                        <select disabled id="editablePhysicalLoadLevel" class="form-control" aria-describedby="editablePhysicalLoadLevel-buttons">
                            <c:forEach var="physicalLoadLevel" items="${profile.physicalLoadLevelList}">
                                <option value="<c:out value="${physicalLoadLevel.id}" />" <c:if test="${physicalLoadLevel.id == profile.physicalLoadLevelId}"><c:out value="selected"/></c:if> >${physicalLoadLevel.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append" id="editablePhysicalLoadLevel-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your PLL</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableGoal" class="col-sm-4 col-form-label">My goal</label>
                        <select disabled id="editableGoal" class="form-control" aria-describedby="editableGoal-buttons">
                            <c:forEach var="goal" items="${profile.goalList}">
                                <option value="<c:out value="${goal.id}" />" <c:if test="${goal.id == profile.goalId}"><c:out value="selected"/></c:if> >${goal.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append" id="editableGoal-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your goal</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableSex" class="col-sm-4 col-form-label">Sex</label>
                        <select disabled id="editableSex" class="form-control" aria-describedby="editableSex-buttons">
                            <c:forEach var="sex" items="${profile.sexList}">
                                <option value="<c:out value="${sex.id}" />" <c:if test="${sex.id == profile.sexId}"><c:out value="selected"/></c:if> >${sex.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append" id="editableSex-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your goal</p>
                </div>
            </div>
            <div class="row no-gutters">
                <div class="col-md-7">
                    <div class="input-group mb-3">
                        <label for="editableDiet" class="col-sm-4 col-form-label">My diet</label>
                        <select disabled id="editableDiet" class="form-control" aria-describedby="editableDiet-buttons">
                            <c:forEach var="diet" items="${profile.dietList}">
                                <option value="<c:out value="${diet.id}" />" <c:if test="${diet.id == profile.dietId}"><c:out value="selected"/></c:if> >${diet.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append" id="editableDiet-buttons">
                            <button class="btn btn-outline-secondary edit-action" type="button" data-toggle="tooltip" title="Edit">
                                <i class="fas fa-edit fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary apply-action d-none" type="button" data-toggle="tooltip" title="Apply">
                                <i class="fas fa-check fa-lg" aria-hidden="true"></i>
                            </button>
                            <button class="btn btn-outline-secondary cancel-action d-none" type="button" data-toggle="tooltip" title="Cancel">
                                <i class="fas fa-times fa-lg" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <p class="pl-2">Your diet</p>
                </div>
            </div>
        </div>
    </div>
    <c:import url="fragments/footer.jsp"/>
</div>


</body>
</html>