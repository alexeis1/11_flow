package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val content: String,
    val published : String,
    val isRead: Boolean = false,
    val likedByMe: Boolean,
    val likes: Int = 0,
) {
    fun toDto() = Post(
        id = id,
        author = author,
        authorAvatar = authorAvatar,
        content = content,
        published = published,
        likes = likes,
        isRead = isRead,
        likedByMe = likedByMe
    )

    companion object {
        fun fromDto(dto: Post, _isRead : Boolean = true) =
            PostEntity(
                id = dto.id,
                author = dto.author,
                authorAvatar = dto.authorAvatar,
                content = dto.content,
                published = dto.published,
                isRead = _isRead,
                likedByMe = dto.likedByMe,
                likes = dto.likes
            )

    }
}

fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(_isRead : Boolean = true): List<PostEntity> = map{PostEntity.fromDto(it, _isRead)}
