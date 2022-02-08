package com.example.wallpaperapp.presentation.wallpapers_screen

import com.example.wallpaperapp.domain.models.Category

enum class CategoriesEnum(val category: Category) {
    PROGRAMMING(
        Category(
            "https://store.hp.com/app/assets/images/uploads/prod/computer-history-timeline-programming-languages1604677346377387.jpg?impolicy=prdimg&imdensity=1&imwidth=150",
            "programming"
        )
    ),
    CARS(
        Category(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRC_XWUKI6vL9AdLzj0XcP9yUaWvUtGt6JOow&usqp=CAU",
            "cars"
        )
    ),
    FOOTBALL(
        Category(
            "https://www.fondationeczema.org/sites/default/files/2020-07/football-760x614px.jpg",
            "football"
        )
    ),
    ANIMALS(
        Category(
            "https://images.theconversation.com/files/285143/original/file-20190722-11355-1peled7.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip",
            "animals"
        )
    )
}
