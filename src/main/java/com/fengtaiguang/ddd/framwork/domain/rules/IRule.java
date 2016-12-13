package com.fengtaiguang.ddd.framwork.domain.rules;

public interface IRule<T>
{
    boolean isSatisfy(T model);
}