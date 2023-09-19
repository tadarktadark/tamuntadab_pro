<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="ck-content" id="ck-content">
		<h2>5. SQL 활용 (이론)</h2>
		<h2>
			<span style="color: hsl(0, 75%, 60%);"><strong>데이터
					모델링의 이해</strong></span>
		</h2>
		<h2>데이터 모델의 이해</h2>
		<h3>모델과 모델링</h3>
		<ul>
			<li>모델 : 사람이 살아가면서 나타날 수 있는 다양한 현상에 대해서 일정한 표기법(ERD)에 의해 표현해놓은
				모형</li>
			<li>모델링
				<ul>
					<li>표기법에 의해 규칙을 가지고 표기하는 것 자체
						<ul>
							<li>표기법 : peter chain, IE/Crow’s foot, barkers</li>
						</ul>
					</li>
					<li>시스템의 대상이 되는 업무를 분석하여 정보시스템으로 구성하는 과정에서 업무의 내용과 정보시스템의 모습을
						적잘한 표기법으로 표현</li>
					<li>해당 업무에 어떤 데이터가 존재하는지 또는 업무가 필요로 하는 정보는 무엇인지를 분석</li>
					<li>정보시스템을 구축하기 위한 데이터 관점의 업무 분석 기법</li>
				</ul>
			</li>
		</ul>
		<h3>모델링의 특징</h3>
		<ul>
			<li>추상화(모형화, 가설적) : 현실 세계의 추상화된 반영, 현실 세계를 일정한 형식에 맞추어 표현</li>
			<li>단순화 : 복잡한 현실 세계를 단순화시켜 표현 → 쉽게 이해</li>
			<li>명확화 : 모델이란 사물 또는 사건에 관한 양상(Aspect)이나 관점(Perspective)을 연관된
				사람이나 그룹을 위하여 명확하게 하는 것 → 애매모호함을 제거하고 정확하게 현상을 기술</li>
		</ul>
		<h3 style="text-align: right;">
			<span style="background-color: hsl(30, 75%, 60%);">모델링의 세 가지
				관점</span>
		</h3>
		<ul>
			<li>데이터 관점
				<ul>
					<li>업무가 어떤 데이터와 관련이 있는지 또는 데이터 간의 관계는 무엇인지</li>
					<li>What, Data</li>
					<li>존재하는 것(유형 엔티티)</li>
				</ul>
			</li>
			<li>프로세스 관점
				<ul>
					<li>업무가 실제 하고 있는 일은 무엇인지 또는 뭐엇을 해야 하는지</li>
					<li>How, Process</li>
					<li>존재하는 것이 하는 것(개념 엔티티)</li>
				</ul>
			</li>
			<li>데이터와 프로세스의 상관 관점
				<ul>
					<li>업무가 처리하는 일의 방법에 따라 데이터는 어떻게 영향을 받고 있는지</li>
					<li>모델링하는 방법(Interaction)</li>
					<li>유형과 개념이 합쳐져 발생 되는 것(사건 엔티티)</li>
				</ul>
			</li>
		</ul>
		<h3>데이터 모델이 제공하는 기능</h3>
		<ul>
			<li>시스템 현재 또는 원하는 모습으로 가시화 → 테이블</li>
			<li>시스템의 구조와 행동을 명세화 → 관계</li>
			<li>시스템을 구축하는 구조화된 틀</li>
			<li>문서화</li>
			<li>다양한 관점 제공</li>
			<li>표현 방법을 제공</li>
		</ul>
		<h3>데이터 모델링의 중요성</h3>
		<ul>
			<li>파급효과(Leverage) : 처음에 잘못 구축하면 계속 유지됨</li>
			<li>간결한 표현(Conciseness) : 단순화, 명확화</li>
			<li>데이터 품질(Data Quality)
				<ul>
					<li>중복(Duplication)</li>
					<li>비유연성(Inflexibility) : 생년월일을 연/월일로 따로 저장 또는 이메일 아이디를
						아이디/이메일로 따로 저장</li>
					<li>비일관성(Inconsistency) : 하나를 변경하면 다른 연관된 애들도 함께 변경</li>
				</ul>
			</li>
		</ul>
		<h3>데이터 모델링의 3단계 진행</h3>
		<ul>
			<li>개념적 데이터 모델링 : 추상화 수준이 높은 상위 수준을 형상</li>
		</ul>
		<figure class="image">
			<img src="./ckupload/cbbd447e6bda44358d91c50416349d90.jpg">
		</figure>
		<h2>엔터티(Entity)</h2>
		<h2>속성(Attribute)</h2>
		<h2>관계(Relationship)</h2>
		<h2>식별자</h2>
		<h2>데이터 모델과 성능</h2>
		<h2>성능 데이터 모델링의 개요</h2>
		<h2>정규화와 성능</h2>
		<h2>반정규화와 성능</h2>
		<h2>대량 데이터에 따른 성능</h2>
		<h2>데이터베이스 구조와 성능</h2>
		<h2>분산 데이터베이스능</h2>
	</div>
</body>
</html>