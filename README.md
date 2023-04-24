# Android_Network_2

깃허브 레포지토리 조회하기 - Retrofit, GitHub Open API, RecyclerView, ListAdapter, Handler

## GitHub Open API

[GitHub Open API](https://docs.github.com/ko/rest?apiVersion=2022-11-28)

- API: 애플리케이션 프로그래밍 인터페이스
- REST: Representational State Transfer, 일종의 네트워크에서 통신을 관리하기 위한 아키텍쳐

## RecyclerView

- [LayoutManager](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.LayoutManager)

## ListAdapter

ListView나 GridView와 같은 AdapterView의 데이터를 관리하고 표시하는 역할.

이 클래스는 여러 아이템을 가진 리스트를 화면에 보여주기 위한 매개체 역할을 수향하며, 사용자가 데이터와 사용자 인터페이스를 독립적으로 관리할 수 있도록 도와준다.

### 예시

1. `activity_main.xml` 파일에 ListView를 추가

```xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" android:orientation="vertical"
    tools:context=".MainActivity">

    <ListView android:id="@+id/listView" android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

2. `MainAcitivty.kt`에서 ListAdapter를 설정

```kotlin
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ArrayAdapter를 생성하고, 리스트 뷰의 어댑터로 설정합니다.
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        // 리스트 뷰의 아이템 클릭 리스너를 설정합니다.
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position)
            Toast.makeText(this, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}
```

문자열 목록을 `items`변수에 저장하고, 이를 ArrayAdapter에 전달하여 간단한 문자열 목록을 ListView에 표시한다. 또한, ListView의 아이템 클릭 리스너를
설정하여 사용자가 선택한 아이템을 표시한다.

현재 프로젝트에서는 Retrofit, GitHub Open API, RecyclerView, ListAdapter, Handler 등을 이용하여 UI와 네트워크 통신을 구현하도록
할 것이다.
