package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragment.databinding.ActivityMainBinding
import com.github.javafaker.Faker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val faker = Faker.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {  // проверка. первый ли раз запущена активити
            val fragment = CounterFragment.newInstance( // создаём фрагмент с помощью метода newInstance(из counterFragment)
                counterValue = 1,
                quote = createQuote()
            )
            supportFragmentManager  // обязательно саппортФрагмент
                .beginTransaction()  // начинаем транзакцию для изменения фрагментов
                .add(R.id.fragmentContainer, fragment) // добавляем фрагмент в контейнер. 1 аргумент - это контейнер, куда нужно добавить, а второй - это фрагмент, который создали выше
                .commit() // закрытие транзакции
        }
    }

    fun createQuote(): String {
        return faker.harryPotter().quote()
    }

    fun getScreensCount(): Int {
        return supportFragmentManager.backStackEntryCount + 1
    }
}