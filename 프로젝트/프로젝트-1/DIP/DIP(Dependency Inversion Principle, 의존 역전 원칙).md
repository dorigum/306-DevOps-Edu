## 1. DIP(의존 역전 원칙)란?

DIP의 핵심은 **"상위 수준의 모듈은 하위 수준의 모듈에 의존해서는 안 되며, 둘 다 추상화에 의존해야 한다"** 는 것입니다.

쉽게 말해, 구현체(클래스)끼리 직접 연결되지 말고 **인터페이스(중간 매개체)** 를 통해 연결되라는 뜻입니다. 그래야 하위 기능이 바뀌어도 상위 시스템이 흔들리지 않거든요.

---
## 2. 원칙 위반 사례 (Bad Case)

고수준 모듈인 `Robot`이 저수준 모듈인 `DuracellBattery`라는 구체적인 클래스에 직접 의존하는 경우입니다.

```java
class DuracellBattery {
    public void supply() { /* 에너지 공급 */ }
}

class Robot {
    private DuracellBattery battery; // 구체적인 클래스에 직접 의존

    public Robot() {
        this.battery = new DuracellBattery();
    }
    
    public void run() {
        battery.supply();
    }
}
```

- **문제점:** 만약 배터리를 `Energizer`로 바꾸고 싶다면, `Robot` 클래스의 코드 자체를 수정해야 합니다. 변화에 유연하지 못하고 결합도가 너무 높죠.
    
---
## 3. 원칙 적용 사례 (Good Case)

배터리를 추상화한 **인터페이스**를 도입하여 의존 관계를 역전시킵니다.

```Java
// 1. 추상화 (인터페이스)
interface Battery {
    void supply();
}

// 2. 하위 모듈 구현
class DuracellBattery implements Battery {
    public void supply() { System.out.println("듀라셀 에너지 공급"); }
}

class EnergizerBattery implements Battery {
    public void supply() { System.out.println("에너자이저 에너지 공급"); }
}

// 3. 상위 모듈 (인터페이스에만 의존)
class Robot {
    private Battery battery;

    // 생성자를 통해 외부에서 주입 (Dependency Injection)
    public Robot(Battery battery) {
        this.battery = battery;
    }

    public void run() {
        battery.supply();
    }
}
```

- **장점:** 이제 `Robot`은 어떤 브랜드의 배터리가 들어오는지 몰라도 됩니다. 단지 `Battery` 규격만 맞으면 되죠. 새로운 배터리가 추가되어도 `Robot`의 코드는 단 한 줄도 바꿀 필요가 없습니다.
    
---
## 핵심 요약

|**구분**|**위반 사례 (Tight Coupling)**|**적용 사례 (Loose Coupling)**|
|---|---|---|
|**의존 대상**|구체 클래스 (`DuracellBattery`)|인터페이스 (`Battery`)|
|**유연성**|변경 시 상위 코드 수정 필요|코드 수정 없이 교체 가능|
|**핵심 키워드**|변화에 취약, 고정됨|**추상화**, **결합도 낮춤**, **주입(DI)**|

DIP를 잘 지키면 코드의 재사용성과 테스트 용이성이 비약적으로 상승합니다.




https://inpa.tistory.com/entry/OOP-%F0%9F%92%A0-%EC%95%84%EC%A3%BC-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EB%8A%94-DIP-%EC%9D%98%EC%A1%B4-%EC%97%AD%EC%A0%84-%EC%9B%90%EC%B9%99