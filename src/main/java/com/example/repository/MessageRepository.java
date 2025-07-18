package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Message;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  Message findByMessageId(Integer messageId);
  Integer deleteByMessageId(Integer messageId);
  List<Message> findAllByPostedBy(Integer postedBy);
}
