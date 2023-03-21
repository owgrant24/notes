# Проект "Заметки"

## Описание

Веб-приложение для хранения заметок

## Скриншоты

#### Главный экран (Категории)

![categories.png](images/categories.png)

#### Заметки выбранной категории

![notes.png](images/notes.png)

#### Просмотр заметки

![note-data.png](images/note-data.png)

<details>
<summary>Дополнительные скриншоты</summary>

#### Экран редактирования категории

![category-edit.png](images/category-edit.png)

#### Экран добавления заметки

![note-add.png](images/note-add.png)

#### Экран редактирования заметки

![note-data-edit.png](images/note-data-edit.png)

</details>

## Запуск

```shell
docker-compose --project-name notes --file docker-compose.yaml up
```

## Технологии

- Kotlin
- Spring Boot
- Spring Data (PostgreSQL, Liquibase)
- Docker
- Thymeleaf
- JavaScript
- Axios
- CKEditor 5
- Bootstrap 5