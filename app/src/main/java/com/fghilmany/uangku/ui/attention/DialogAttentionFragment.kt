package com.fghilmany.uangku.ui.attention

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fghilmany.uangku.core.utils.MESSAGE
import com.fghilmany.uangku.databinding.FragmentDialogAttentionBinding
import com.fghilmany.uangku.ui.base.BaseDialogFragment

class DialogAttentionFragment : BaseDialogFragment() {
    private var _binding : FragmentDialogAttentionBinding? = null
    private val binding get() = _binding!!

    private var message: String? = null

    companion object {
        val TAG: String = this::class.java.simpleName
        @JvmStatic
        fun newInstance(message: String) =
            DialogAttentionFragment().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE, message)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDialogAttentionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            tvMessage.text = message
            btnOk.setOnClickListener {
                dismiss()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(MESSAGE)
        }
    }

    override fun onStart() {
        super.onStart()
        setFullScreen()
    }

}