** Тестовое задание **
* Вакансия: java-разработчик *
* Выполнил: Садовой Данила Иванович *
* Телефон (tg, whatsup): 89659255311 *

..\pool - проект выполненный в Intellij Idea.
..\classes - папка с основными файлами проекта для быстрого доступа.

	База данных использовавшаяся в проекте - postgres (pgAdmin 4).
	Структура БД:
	
		Таблица клиентов
		CREATE TABLE clients (
			client_id SERIAL PRIMARY KEY,
			name VARCHAR(50) NOT NULL,
			phone VARCHAR(15),
			email VARCHAR(50)
		);
		
		Таблица записей
		CREATE TABLE reserves (
			reserve_id SERIAL PRIMARY KEY,
			client_id INT REFERENCES clients(client_id) ON DELETE CASCADE,
			datetime TIMESTAMP NOT NULL,
			UNIQUE(client_id, time)
		);
		
	Описание java проекта:
	IJ: IntelliJ IDEA 2024.1.4 (Community Edition)
	JDK: Eclipse Adoptium\jdk-11.0.18.10-hotspot
	
	Этапы разработки:
	1. Изучение теоритического материала (Spring Boot, REST API).
	2. Создание базы данных в pgAdmin 4.
	3. Создание Maven проекта, подключение зависимостей.
	4. Определение структуры проекта.
		Главный класс программы:
			Main
		Классы сущностей entities:
			Client
			Reserve
		Интерфейсы репозиториев:
			ClientsRepo
			ReservesRepo
		Классы сервисов:
			ClientService
			ReserveService
		Классы контроллеров:
			ClientController
			ReserveController
		Дополнительные классы:
			Классы сущности для реквестов опредленного формата:
				ReserveRequest
				CancelRequest
			Класс с данными о незанятых временных местах:
				AvailableTime
		Классы тестов:
			ClientServiceTest
	5. Разработка проекта.
	6. Тестирование URL запросов.
	
	Дальнейшие планы на проект:
		Организация дополнительных тестов для сервисов и контроллеров с помощью юнит-тестов и SpringBootTest.
		Создание глобального обработчика ошибок для вынесения ResponceEntity из классов-контроллеров.
		Создание парсера для разных форматов ввода даты.
		Отдельные графики для праздничных дней.

	Нерешенные вопросы:
		Почему то поле id всех ответов всегда выводится в конце структуры:
		[
			{
				"name": "Сидр Иванович",
				"id": 1
			},
			{
				"name": "Егор Петрович",
				"id": 2
			}
		]
		
		[
			{
				"name": "Сидр Иванович",
				"phone": "1234567890",
				"email": "ivan@mail.ru",
				"id": 1
			}
		]
	
	Используемые ссылки при подготовке:
	https://docs.spring.io/spring-boot/index.html
	https://habr.com/ru/articles/435144/
	https://dzone.com/articles/introducing-spring-boot
	https://habr.com/ru/articles/675716/?_gl=1%2Awplp0y%2A_ga%2AbDgtSl9VN1dxdERNYS1SMkp1ZlNTLVd6d2pwU2RRWVFmX1c3bm9mQWRhSkJCTHpHZ2dwNm5qOXplTFN1WEs2Vw..%2A_ga_S28W1WC23F%2AMTczMDY2MTU0NS4xLjEuMTczMDY2MTU0NS4wLjAuMA
	https://tproger.ru/articles/spring-boot-bystroe-znakomstvo-i-start-na-primere-prostogo-veb-prilozhenija
	https://habr.com/ru/companies/otus/articles/780090/
	https://www.vogella.com/tutorials/Mockito/article.html