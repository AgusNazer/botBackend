package com.agusdev.bottrading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agusdev.bottrading.entity.BotEntity;

public interface BotRepository extends JpaRepository<BotEntity, Long> {

}
