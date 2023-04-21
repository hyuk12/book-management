import {atom} from "recoil";

export const refreshState = atom({
    key: 'refreshState',
    default: true
});

// 상태 하나하나를 atom 이라고 부른다.
export const authenticatedState = atom({
    key: 'authenticatedState', // 변수명 그대로 사용한다.
    default: false // 기본값
});