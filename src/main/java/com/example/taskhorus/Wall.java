package com.example.taskhorus;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
public class Wall implements Structure {

    private List<Block> blocks;
    private final CompositeBlock compositeBlock;

    public Wall(CompositeBlock compositeBlock) {
        this.compositeBlock = compositeBlock;
        this.blocks = compositeBlock.getBlocks();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return blocks.size();
    }
}
