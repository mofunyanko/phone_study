package com.example.todoapp

class ToDoData {
    var title: String? = null
    var content: String? = null
    var firebaseKey: String? = null

    constructor(key: String?, title: String?, content: String?) {
        firebaseKey = key
        this.title = title
        this.content = content
    }
}