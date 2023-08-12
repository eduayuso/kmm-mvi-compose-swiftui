package dev.eduayuso.kmcs

object AppConstants {

    object RouteIds {

        const val home = "home"
        const val postList = "postList"
        const val postDetail = "postDetail"
        const val tagList = "tagList"
        const val tagDetail = "tagDetail"
        const val userList = "userList"
        const val userDetail = "userDetail"
    }

    object Apis {

        object DummyApi {

            const val url = "https://dummyapi.io/data/v1/"
            const val users = "user"
            const val posts = "post"
            const val tags = "tag"
            const val comments = "comment"
        }
    }

    object Http {

        object Headers {

            const val appId = "app-id"
        }
    }

    object ViewArguments {

        const val userId = "userId"
        const val postId = "postId"
        const val tagId = "tagId"
    }
}
