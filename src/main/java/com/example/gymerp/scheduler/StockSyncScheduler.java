package com.example.gymerp.scheduler;

import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.gymerp.repository.StockDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockSyncScheduler {
    
    private final StockDao stockDao;
    
    // 매일 새벽 3시에 실행
    @Scheduled(cron = "0 0 3 * * *")
    public void syncCurrentStock() {
        // 1. 불일치 목록 조회
        List<Map<String, Object>> mismatchList = stockDao.findMismatchedProducts();
        
        if (mismatchList.isEmpty()) {
            log.info("재고 동기화: 불일치 없음");
            return;
        }
        
        // 2. 각 항목 상세 로그
        for (Map<String, Object> item : mismatchList) {
            log.warn("재고 보정 - 상품ID: {}, 상품명: {}, 기존: {}개 → 보정: {}개",
                item.get("PRODUCTID"),
                item.get("NAME"),
                item.get("CURRENTSTOCK"),
                item.get("CALCULATEDSTOCK")
            );
        }
        
        // 3. 일괄 업데이트
        int updatedCount = stockDao.syncAllProductStock();
        log.warn("재고 동기화 완료: 총 {}건 보정됨", updatedCount);
    }
}