package dev.eduayuso.kmcs.presentation

import dev.eduayuso.kmcs.executor.MainIOExecutor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface IViewModel<Event, State, Effect> {

    val state: StateFlow<State>
    val event: SharedFlow<Event>
    val effect: Flow<Effect>

    fun setEvent(event: Event)
}

abstract class ViewModel<Event: UIEvent, State: UIState, Effect: UIEffect>:

    IViewModel<Event, State, Effect>,
    MainIOExecutor() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = state.value

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event = _event.asSharedFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    private val _effect: Channel<Effect> = Channel()
    override val effect = _effect.receiveAsFlow()

    init {

        subscribeEvents()
    }

    private fun subscribeEvents() {

        launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: Event)

    override fun setEvent(event: Event) {

        val newEvent = event
        launch { _event.emit(newEvent) }
    }

    protected fun setState(reduce: State.() -> State) {

        val newState = currentState.reduce()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {

        val effectValue = builder()
        launch { _effect.send(effectValue) }
    }

}