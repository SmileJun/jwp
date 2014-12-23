2014년 개발 경험 프로젝트
=========

1. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.

* server를 실행하면 먼저 ServletContextListener를 구현한 객체들이 시작을 인지하면서 contextInitialized()메서드를 실행하여 각종 자원을 준비한다. 자원들은 ServletContext객체에 저장된다.
 - 이 때 RequestMapping에 대한 정보를 생성하는데 /list.next,... 에 대한 Controller Mapping을 준비해둔다.
 - DEFAULT_REQUEST_MAPPING이라는 이름으로 RequestMapping에 대한 정보를 ServletContext객체에 저장한다.
* 먼저 url을 '/'를 요청하였으므로 web.xml에 등록되어 있는 welcome-file-list인 index.jsp를 제일 먼저 실행한다.
* 여기 이 jsp에서는 '/list.next'로 리다이렉트를 하고 있다
* 이에 의해 *.next와 mapping되어 있는 FrontController가 해당 요청을 받아들이고 init(), service(), destroy() 주기를 수행한다.
 - init()을 수행하면서 변수 rm에 ServletContext에 저장해 두었던 Mapping정보를 가져온다
 - service()에서는 controller변수에 어떤 RequestMapping 객체의 findController메서드를 실행하면서 어떤 Controller를 사용할 것인지 찾는다. 현재 요청은 /list.next였으므로 mapping에 따라 ListController() 객체를 반환한다. 이 ListController객체의 execute()메서드를 실행한다
 - execute()에서는 Dao 객체를 통해 질문 리스트를 받아오고, 이를 JstlView객체에 Questions라는 이름으로 준비해둔다.
 - 다시 service()를 계속 수행하여 View 객체의 render메서드를 통해 jsp로 forward되어 Questions에 저장된 질문 목록을 보게 된다.

2. 
