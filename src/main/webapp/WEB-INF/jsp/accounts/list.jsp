<%@ include file="../header.jspf" %>
<!-- %@ taglib prefix="spring" uri="http://www.springframework.org/tags"% -->

<h1>
	<fmt:message key="accounts.list.title" />: ${customer.name}
</h1>

<div>
	<a href="accounts.xml"><fmt:message key="accounts.list.command.asXml"/></a>
	&#160;
	<a href="accounts.json"><fmt:message key="accounts.list.command.asJson"/></a>
	&#160;
	<a href="/browser/index.html#/rest/user/${customer.username}" target="hal"><fmt:message key="accounts.list.command.asHal"/></a>
</div>

<hr/>

<div id="entityDetails">
	<div id="customerDetails">
		<ul>
			<li>
				<fmt:message key="label.Account.customer"/>: ${customer.number}
			</li>
			<li>
				<fmt:message key="label.Account.name"/>: ${customer.name}
			</li>
			<li>
				<fmt:message key="label.Account.dateOfBirth"/>: <fmt:formatDate value="${customer.dateOfBirth}"/>
			</li>
			<li>
				<fmt:message key="label.Account.email"/>: ${customer.email}
			</li>
			<li>
				<fmt:message key="label.Account.receiveNewsletter"/>: 
				<fmt:message key="label.${customer.receiveNewsletter}"/>
			</li>
			<li>
				<fmt:message key="label.Account.receiveMonthlyEmailUpdate"/>: 
				<fmt:message key="label.${customer.receiveMonthlyEmailUpdate}"/>
			</li>
		</ul>
	</div>

	<div id="accounts">
		<h2>
			<fmt:message key="label.Account.accounts"/>
		</h2>
		<table style="margin-left: 3em">
			<thead>
				<tr>
					<th>
						<fmt:message key="label.Account.number"/>
					</th>
					<th>
						<fmt:message key="label.Account.type"/>
					</th>
					<th>
						<fmt:message key="label.Account.balance"/>
					</th>
					<th style="text-align: center">
						<fmt:message key="label.Account.creditCardNumber"/>
					</th>
				</tr>
			</thead>
			<c:forEach var="account" items="${customer.accounts}">
				<tr>
		 			<td>
						<spring:url var="showUrl2" value="/accounts/{number}.html">
							<spring:param name="number" value="${account.number}" />
						</spring:url>
						<a href="/accounts/${account.number}.html">${account.number}</a>
					</td>
					<td style="text-align: center">
						${account.type}
					</td>
					<td style="text-align: right">
						<fmt:formatNumber value="${account.balance.value}" type="currency" /> 
					</td>
					<td style="text-align: center">
						<c:choose>
							<c:when test="${!empty account.creditCardNumber}">
								${account.creditCardNumber}
							</c:when>
							<c:otherwise>N/A</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>	
		</table>
	</div>
</div>

<%@ include file="../footer.jspf" %>
