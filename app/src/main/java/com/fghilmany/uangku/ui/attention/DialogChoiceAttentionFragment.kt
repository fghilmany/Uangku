package com.fghilmany.uangku.ui.attention

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fghilmany.uangku.R
import com.fghilmany.uangku.core.utils.MESSAGE
import com.fghilmany.uangku.databinding.FragmentDialogChoiceAttentionBinding
import com.fghilmany.uangku.ui.base.BaseDialogFragment

class DialogChoiceAttentionFragment : BaseDialogFragment() {

    private var _binding: FragmentDialogChoiceAttentionBinding? = null
    private val binding get() = _binding!!

    companion object{
        val TAG: String = this::class.java.simpleName
        var listener: AttentionListener? = null
        fun newInstance(listener: AttentionListener, message: String): DialogChoiceAttentionFragment {
            this.listener = listener
            val args = Bundle()
            args.putString(MESSAGE, message)
            val fragment = DialogChoiceAttentionFragment()
            fragment.arguments = args
            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDialogChoiceAttentionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = arguments?.getString(MESSAGE)

        with(binding){
            tvMessage.text = message
            btnConfirm.setOnClickListener {
                listener?.onConfirm()
                dismiss()
            }
            btnCancel.setOnClickListener {
                listener?.onCancel()
                dismiss()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        setFullScreen()
    }
}
