package ru.netology.nmedia.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.netology.nmedia.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT COALESCE(COUNT(id), 0) FROM PostEntity WHERE isRead=0")
    suspend fun unreadCount() : Long

    @Query("SELECT COALESCE(COUNT(*), 0) FROM PostEntity WHERE isRead=0")
    fun unreadCountFlow(): Flow<Long>

    @Query("SELECT * FROM PostEntity WHERE isRead=1 ORDER BY id DESC")
    fun getAll(): Flow<List<PostEntity>>

    @Query("SELECT COUNT(*) == 0 FROM PostEntity")
    suspend fun isEmpty(): Boolean

    @Query("SELECT COUNT(*) FROM PostEntity")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostEntity>)

    @Query("UPDATE PostEntity SET isRead=1 WHERE isRead=0")
    suspend fun setAllPostsRead()

    @Query("UPDATE PostEntity SET isRead=1 WHERE id = :id")
    suspend fun markPostsAsRead(id: Long)

    @Query("SELECT * FROM PostEntity WHERE id = :id")
    suspend fun getPostById(id: Long) : PostEntity?

    @Query("DELETE FROM PostEntity WHERE id = :id")
    suspend fun removeById(id: Long)
}
